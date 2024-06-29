package com.example.grantsmanagement.GrantsManagement.utils;

import com.example.grantsmanagement.GrantsManagement.dto.TemplateVariablesDto;

import java.lang.reflect.Field;

public class StringUtils {

    public static String getDefaultEmailTemplate() {
        String defaultTemplate = "{foundation_name} is sending money to nonprofit {name} at address {address}. Good Job!!";
        return defaultTemplate;
    }

    public static String populateTemplates(String template, TemplateVariablesDto templateVariablesDto) {
        Field[] fields = templateVariablesDto.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue = field.get(templateVariablesDto);
                if (fieldValue != null) {
                    template = template.replace("{" + fieldName + "}", fieldValue.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return template;
    }
}
