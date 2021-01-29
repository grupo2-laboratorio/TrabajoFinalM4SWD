package com.devops.dxc.devops.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Serie {
    private String fecha;
    private String valor;
}
