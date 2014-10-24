-- name: create-user-cache-table!
-- Create user cache table.
CREATE TABLE IF NOT EXISTS user_cache (
    id INTEGER PRIMARY KEY,
    username TEXT,
    content TEXT,
    updated_at INTEGER DEFAULT (strftime('%s', 'now'))
)
