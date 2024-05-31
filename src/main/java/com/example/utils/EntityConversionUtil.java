package com.example.utils;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;

public class EntityConversionUtil {

    private final static ModelMapper modelMapper = new ModelMapper();

    //    类型转换工具，返回destinationType类型的实体
    public static <D> D entityToVo(@NotNull Object source, @NotNull Class<D> destinationType) {

        return modelMapper.map(source, destinationType);
    }

//    public static <D> D entityToVo(Object source, Class<D> destinationType) {
//        if (ObjectUtils.isEmpty(source) || destinationType == null) {
//            return null;
//        }
//
//        D map = modelMapper.map(source, destinationType);
//        try {
//            Field sexField = map.getClass().getDeclaredField(Sex.getName());
//            sexField.setAccessible(true);
//            String sexValue = (String) sexField.get(map);
//            for (Sex sex : Sex.values()) {
//                if (sexValue.equals(sex.key)){
//                    sexField.set(map, sex.value);
//                    break;
//                }
//            }
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            return map;
//        }
//
//        return map;
//    }

//    @Getter
//    enum Sex{
//        MAN("M", "男"),
//        FEMALE("F", "女");
//
//        private final String key;
//
//        private final String value;
//
//        Sex(String key, String value) {
//            this.key = key;
//            this.value = value;
//        }
//
//        public static String getName(){
//            return "sex";
//        }
//
//    }

}
