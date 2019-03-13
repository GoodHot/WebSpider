-- eval redisDistributeLock.lua 1 key

-- key
local key = KEYS[1]

if(1 == redis.call("exists", key))
then
    return false
else
    redis.call("set", key, 1)
    redis.call("EXPIRE", key, 300)
    return true
end