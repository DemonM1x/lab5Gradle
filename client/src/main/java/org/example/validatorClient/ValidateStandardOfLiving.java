package org.example.validatorClient;

import org.example.collection.StandardOfLiving;

public class ValidateStandardOfLiving extends ValidateAbstract<StandardOfLiving>{
    public  ValidateStandardOfLiving(){
        super("City.StandardOfLiving" , "");
    }

    @Override
    public Class<StandardOfLiving> getType() {
        return StandardOfLiving.class;
    }

    @Override
    public boolean validate(String value) {
        try {
            var valueEnum = StandardOfLiving.valueOf(value);
            return true;
        }catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean validate(String[] value) {
        return false;
    }

}
