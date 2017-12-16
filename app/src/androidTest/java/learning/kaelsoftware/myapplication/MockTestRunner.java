package learning.kaelsoftware.myapplication;

/**
 * Created by locAttack on 16/12/2017.
 */

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;


public class MockTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, MyMockApplication.class.getName(), context);
    }
}
