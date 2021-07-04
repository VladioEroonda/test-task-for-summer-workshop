package com.github.vladioeroonda.testtask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Map;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TargetPage {

    @NotBlank
    @Size(min = 6, max = 500, message = "Ссылка должна быть от 6 до 500 символов")
    public String address;
    public Map<String, Integer> countedWords;

}
