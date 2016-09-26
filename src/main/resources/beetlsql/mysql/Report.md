list
===
select 
    r.*,d.name AS DIC_STATUS 
from tb_report r
left join (select num,name from tfw_dict where code = 903) d on r.status = d.num
