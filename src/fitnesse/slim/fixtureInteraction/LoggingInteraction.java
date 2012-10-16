package fitnesse.slim.fixtureInteraction;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LoggingInteraction extends DefaultInteraction {
  @Override
  public Object methodInvoke(Method method, Object instance, Object... convertedArgs) throws InvocationTargetException, IllegalAccessException {
    long startTime = System.nanoTime();
    Object o = super.methodInvoke(method, instance, convertedArgs);
    long endTime = System.nanoTime();
    System.out.println("methodInvoke : " + createTimestamp(method, startTime, endTime));
    return o;
  }

  @Override
  public Object newInstance(Constructor constructor, Object... initargs) throws InvocationTargetException, InstantiationException, IllegalAccessException {
    long startTime = System.nanoTime();
    Object o = super.newInstance(constructor, initargs);
    long endTime = System.nanoTime();
    System.out.println("newInstance : " + createTimestamp(constructor, startTime, endTime));
    return o;
  }

  private String createTimestamp(Method method, long startTime, long endTime) {
    return method.getDeclaringClass() + "." + method.getName() + " = " + (endTime - startTime);
  }

  private String createTimestamp(Constructor method, long startTime, long endTime) {
    return method.getDeclaringClass() + "." + method.getName() + " = " + (endTime - startTime);
  }
}
