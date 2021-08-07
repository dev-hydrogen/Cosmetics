package com.ilm9001.cosmetics.util;

import java.util.Arrays;
import java.util.Optional;

//types of player cosmetics
public enum CosmeticType {
   INVENTORY_ITEM ((byte)4),
   HAT ((byte)3),
   CHESTPLATE ((byte)2),
   LEGGINGS ((byte)1),
   BOOTS ((byte)0);
   
   private final byte id;
   CosmeticType(byte i) {
      this.id = i;
   }
   
   public byte getID() {
      return id;
   }
   
   public static Optional<CosmeticType> getFromID(byte value) {
      return Arrays.stream(values()).filter(legNo -> legNo.id == value).findFirst();
      //https://stackoverflow.com/questions/11047756/getting-enum-associated-with-int-value
   }
   
}
