package com.example.backend_security.infrastucture.adapter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.ElementImage;
import com.example.backend_security.infrastucture.http.httprestentities.ElementHttpRestEntity;
import com.example.backend_security.infrastucture.http.httprestentities.ElementImageHttpRestEntity;

@Component
public class TypeToDomainAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String,Class<? extends ElementHttpRestEntity>> elementMap = new HashMap<>();


    public Element convert(ElementHttpRestEntity object) {
        this.elementMap.put("IMAGE", ElementImageHttpRestEntity.class);

        ElementHttpRestEntity image =new ElementImageHttpRestEntity(50, "IMAGE", "URL33");
        Class<? extends ElementHttpRestEntity> clazz = this.elementMap.get(object.getType());
        System.out.println(clazz);
        Element elementData = new ElementImage(99,"IMAGE","IMGURL99");
        return elementData;
    }
}
