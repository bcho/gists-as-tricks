# coding: utf-8

'''
    Fabric tasks for deploying gists-as-tricks.

    Before running the script, make sure you have install [fabric][0].

    [0]: http://www.fabfile.org/installing.html
'''

from fabric.api import run, env, cd


# Make bash handle with my aliases etc.
env.shell = '/bin/bash -l -i -c'

CODEBASE_PATH = env.get('codebase_path', '/srv/apps')
EXPOSE_PORT = env.get('expose_port', 9344)
IMAGE_TAG = env.get('docker_image_tag', 'gists-as-tricks')
CONTAINER_NAME = env.get('docker_container_name', 'gists-as-tricks')


def update_codebase():
    with cd(CODEBASE_PATH):
        run('git fetch')
        run('git reset --hard origin/master')


def build_jar():
    with cd(CODEBASE_PATH):
        run('lein compile')
        run('lein uberjar')


def build_docker_image():
    with cd(CODEBASE_PATH):
        run('docker build -t {0} .'.format(IMAGE_TAG))


def start_container():
    run('docker run -d -p {0}:9344 --name {1} {2}'.format(
        EXPOSE_PORT,
        CONTAINER_NAME,
        IMAGE_TAG
    ))


def restart_container():
    run('docker restart {0}'.format(CONTAINER_NAME))


def deploy():
    update_codebase()
    build_jar()
    build_docker_image()
    restart_container()
