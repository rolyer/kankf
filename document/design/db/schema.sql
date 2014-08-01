##UPDATE game SET start_time = '2014-08-01 21:35:40'
##WHERE id > 2000;

##UPDATE game SET start_time = NOW()
##WHERE id > 1000;

SELECT id, user_name, name, start_time, server_name, url, category, gift_name, platform, gmt_created, gmt_modified 
FROM game 
WHERE 1=1 AND YEAR(start_time) = YEAR('2014-08-01 21:41:12.111') 
AND DAY(start_time) = DAY('2014-08-01 21:41:12.111') AND MONTH
(start_time) = MONTH('2014-08-01 21:41:12.111') ORDER BY id DESC LIMIT 0, 50