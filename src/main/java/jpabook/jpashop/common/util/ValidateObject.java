package jpabook.jpashop.common.util;


import static org.jboss.jandex.PrimitiveType.*;

/**객체 자료형 검증
 * The type Validate object.
 */
public class ValidateObject {

    public boolean validate(Object o){
        return Primitive.INT == o;
    }

    public static void main(String[] args) {
        ValidateObject validateObject = new ValidateObject();
        System.out.println(validateObject.validate(1));
    }
}
