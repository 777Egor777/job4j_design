select p.name, c.name
from person p left join company c on p.company_id = c.id
where p.company_id != 5;

select c.name as company, count(p.id) as persons_in_company
from company c left join person p on c.id = p.company_id
group by c.name
order by count(p.id) desc
limit 1;