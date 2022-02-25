package com.work.demo;

import lombok.*;

@Data
@NoArgsConstructor
public class Items {
    private int id;
    private String description;
    private boolean checked;

    public Items (String description, boolean checked){
        this.description = description;
        this.checked = checked;
    }

    public void setId(int id){
        this.id = id;
    }
}
