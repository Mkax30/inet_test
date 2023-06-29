package com.etnetera.hr.mapper;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.data.Version;
import com.etnetera.hr.model.JavaScriptFrameworkDto;
import com.etnetera.hr.model.VersionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FrameworkMapper {

    FrameworkMapper INSTANCE = Mappers.getMapper(FrameworkMapper.class);

    @Mapping(source = "name", target = "name")
    JavaScriptFrameworkDto frameworkToFrameworkDto(JavaScriptFramework javaScriptFramework);

    @Mapping(source = "name", target = "name")
    JavaScriptFramework frameworkDtoToFramework(JavaScriptFrameworkDto javaScriptFrameworkDto);

    VersionDto versionToVersionDto(Version version);

    Version versionDtoToVersion(VersionDto versionDto);

//    Set<String> mapVersions(Set<String> versions);
}
