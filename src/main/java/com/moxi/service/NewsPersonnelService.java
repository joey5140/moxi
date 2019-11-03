package com.moxi.service;

import com.moxi.model.NewsPersonnel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NewsPersonnelService {
	
	@Select("SELECT * FROM `moxi`.`personnel` where id = #{id};")
	NewsPersonnel findById(NewsPersonnel newsPersonnel);
	
	@Select({
		"<script>",
		"SELECT * FROM `moxi`.`personnel`",
		"WHERE state = 1",
			"<when test='name!=null'>",
				"AND name LIKE CONCAT('%',#{name},'%')",
			"</when>",
			"order by addDate desc limit #{start},#{end}",
		"</script>"
	})
	List<NewsPersonnel> list(NewsPersonnel newsPersonnel);
	
	@Select({
		"<script>",
		"SELECT count(*) FROM `moxi`.`personnel`",
		"WHERE state = 1",
			"<when test='name!=null'>",
				"AND name LIKE CONCAT('%',#{name},'%')",
			"</when>",
		"</script>"
	})
	int count(NewsPersonnel newsPersonnel);
	
	@Insert("INSERT INTO `moxi`.`personnel`(`id`, `name`, `sex`, `sr`, `phone`, `homeadd`, `email`, `departmentid`, `departmentname`, `addDate`, `state`) VALUES (null, #{name}, #{sex}, #{sr}, #{phone}, #{homeadd}, #{email}, #{departmentid}, #{departmentname}, now(), 1);")
	int insert(NewsPersonnel newsPersonnel);

	@Update("UPDATE `moxi`.`personnel`SET name =#{name}, sex=#{sex}, sr=#{sr}, phone=#{phone}, homeadd=#{homeadd}, email=#{email}, departmentid=#{departmentid}, departmentname=#{departmentname} WHERE `id` = #{id};")
	int update(NewsPersonnel newsPersonnel);
	
	@Update("UPDATE `moxi`.`personnel`SET `state` = #{state} WHERE `id` = #{id};")
	int updateState(NewsPersonnel newsPersonnel);
}
