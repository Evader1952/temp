<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${package}.mapper.${className?cap_first}Mapper">
    <resultMap id="BaseResultMap" type="${fullClassName}">
        <#list model_columns as model>
            <#if model.name='id'>
            <id column="${model.name?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}" property="${model.name}" jdbcType="${model.jdbcType}"/>
            <#else>
            <result column="${model.name?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}" property="${model.name}" jdbcType="${model.jdbcType}"/>
            </#if>
        </#list>
    </resultMap>

    <sql id="table">${className?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}</sql>
    <sql id="Base_Column_List">
        <#list model_columns as model>
            `${model.name?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}`<#if model_has_next>,</#if>
        </#list>
    </sql>
    <sql id="Base_Where_Clause">
        <where>
            <trim prefixOverrides="and">
                <#list model_columns as model>
                    <#if model.jdbcType='VARCHAR'>
                    <if test="${model.name} != null and ${model.name} != ''">
                        AND `${model.name?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}`=${r"#{"}${model.name}}
                    </if>
                    <#else>
                    <if test="${model.name} != null">
                        AND `${model.name?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}`=${r"#{"}${model.name}}
                    </if>
                    </#if>
                </#list>
            </trim>
        </where>
        <if test="offset == null and limit != null">
            limit ${r"#{limit}"}
        </if>
        <if test="offset != null and limit != null">
            limit ${r"#{offset}"}, ${r"#{limit}"}
        </if>
    </sql>

    <!-- 查询总数 -->
    <select id="selectCount" resultType="java.lang.Long" parameterType="java.util.Map">
        SELECT count(id)
        FROM
        <include refid="table"/>
        <include refid="Base_Where_Clause"/>
    </select>

    <!-- 查询 -->
    <select id="select" resultMap="BaseResultMap" parameterType="java.util.Map">
        SELECT
        <include refid="Base_Column_List"/>
        FROM
        <include refid="table"/>
        <include refid="Base_Where_Clause"/>
    </select>

    <insert id="insert" parameterType="${fullClassName}" useGeneratedKeys="true"
            keyProperty="id">
        INSERT INTO
        <include refid="table"/>
        (
        <#list model_columns as model>
            `${model.name?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}`<#if model_has_next>,</#if>
        </#list>
        )
        VALUES(
        <#list model_columns as model>
            ${r"#{"}${model.name}}<#if model_has_next>,</#if>
        </#list>
        )
    </insert>

    <insert id="insertBatch" parameterType="java.util.List" keyProperty="id" useGeneratedKeys="true">
        INSERT INTO
        <include refid="table"/>
        (
        <#list model_columns as model>
            `${model.name?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}`<#if model_has_next>,</#if>
        </#list>
        )
        VALUES
        <foreach collection="list" item="cm" index="index" separator=",">
            (
        <#list model_columns as model>
            ${r"#{cm."}${model.name}}<#if model_has_next>,</#if>
        </#list>
            )
        </foreach>
    </insert>

    <select id="selectById" resultMap="BaseResultMap" parameterType="java.lang.Long">
        SELECT
        <include refid="Base_Column_List"/>
        FROM
        <include refid="table"/>
        WHERE id=${r"#{id}"}
    </select>

    <update id="updateById" parameterType="${fullClassName}">
        UPDATE
        <include refid="table"/>
        <set>
        <#list model_columns as model>
            <#if model.name!='id'>
                <#if model.jdbcType='VARCHAR'>
            <if test="${model.name} != null and ${model.name} != ''">
                 `${model.name?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}`=${r"#{"}${model.name}},
            </if>
                <#else>
            <if test="${model.name} != null">
                 `${model.name?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}`=${r"#{"}${model.name}},
            </if>
                </#if>
            </#if>
        </#list>
        </set>
        WHERE id=${r"#{id}"}
    </update>
</mapper>