
<#list model_tables as table>

CREATE TABLE `${table.name?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}` (
 <#list table.columns as model>
 <#if model.name='id'>
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
 <#else>
    `${model.name?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}` ${model.dbType?lower_case} DEFAULT NULL,
 </#if>
</#list>
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

</#list>