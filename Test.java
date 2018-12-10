public class Test
{
  public static void main(String[] paramArrayOfString)
  {
    paramArrayOfString = new MyArrayList();
    paramArrayOfString.add(Integer.valueOf(1));
    paramArrayOfString.add(Integer.valueOf(2));
    new MyArrayList();
    Object localObject = (MyArrayList)paramArrayOfString.clone();
    ((MyArrayList)localObject).addAll(paramArrayOfString);
    ((MyArrayList)localObject).set(2, Integer.valueOf(3));
    ((MyArrayList)localObject).set(3, Integer.valueOf(4));
    paramArrayOfString.size();
    paramArrayOfString.clear();
    ((MyArrayList)localObject).indexOf(Integer.valueOf(3));
    localObject = (Object[])((MyArrayList)localObject).toArray();
    paramArrayOfString.isEmpty();
  }
}


/* Location:              C:\Users\sherl\Desktop\dex2jar-2.0\dex2jar-2.0\SampleForPaper\classes0-dex2jar.jar!\Test.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */