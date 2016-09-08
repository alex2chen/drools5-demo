import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Parameter_test
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/8/5
 */
@RunWith(Parameterized.class)
public class Parameter_test {
    private int num1, num2, result;

    public Parameter_test(int num1, int num2, int result) {
        this.num1 = num1;
        this.num2 = num2;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection getParamters() {
        /*Object*/
        Integer[][] object = {{1, 2, 3}, {0, 0, 0}, {-1, 1, 0}, {-1, -2, -3}};
        return Arrays.asList(object);
    }

    @Test
    public void testAdd() {
        int result = this.num1 + this.num2;
        Assert.assertEquals(this.result, result);
    }
}
