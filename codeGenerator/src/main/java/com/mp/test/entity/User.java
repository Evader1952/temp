package com.mp.test.entity;

import com.mp.common.entity.Identifiable;
import lombok.Data;

@Data
public class User implements Identifiable<Long> {

    private Long id;

    private String title;

    private String icon;

    private String path;

}
