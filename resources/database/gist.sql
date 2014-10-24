-- name: sql-get-by-user-with-ttl
-- Get user's unexpired gist records from cache by username.
SELECT content
FROM user_cache
WHERE
    username = :username AND
    strftime('%s', 'now') - updated_at < :ttl
LIMIT 1


-- name: sql-get-user-cache-count
-- Get user's cached records count.
SELECT COUNT(*) AS count FROM user_cache WHERE username = :username


-- name: sql-create-user-cache!
-- Create a gist records for user.
INSERT INTO user_cache (username, content) VALUES (:username, :content)


-- name: sql-set-user-cache!
-- Set user cache.
UPDATE user_cache
SET
    content = :content,
    updated_at = (strftime('%s', 'now'))
WHERE
    username = :username
