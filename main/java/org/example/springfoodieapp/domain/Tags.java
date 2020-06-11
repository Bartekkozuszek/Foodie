package org.example.springfoodieapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Arrays;

public enum Tags {
   NONE, LCHF, PALEO, VEGETERIAN, VEGAN, LAKTOS, GLUTEN;

/*   private String value;

   Tags(String val) {
      this.value = val;
   }

   Tags() {

   }

   public String getValue() {
      return value;
   }

   public static  Tags[] getSortedValue() {
      Tags[] values = Tags.values();
     *//* Arrays.asList(Arrays.toString(values).equals(values));*//*
      return values;
   }*/

}
