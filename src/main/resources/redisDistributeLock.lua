-- eval redisDistributeLock.lua 1 key

-- key
local key = KEYS[1]

if(1 == redis.call("exists", key))
then
    -- 存在，获取锁失败
    return false
else
    -- 不存在
    redis.call("set", key, 1)
    -- 5 min 过期
    redis.call("EXPIRE", key, 300)
    -- 获取成功
    return true
end