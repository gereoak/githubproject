/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 986639
 */
public class NameRepository implements Aggregate{
    private String names[][] = {{"Rob" , "-" ,"Jul"} , {"Lor", "Pat","Ken"}};
    
    @Override   
    public Iterator getIterator() {
    return new NameIterator();
    }
    private class NameIterator implements Iterator 
    {
        int index_X;
        int index_Y;
        @Override
        public boolean hasNext() {

        if(index_X <= names.length && index_Y <= names.length){
        return true;
        }
        else if (index_Y > names.length && index_X < names.length -1)
        {
            index_X++;
            index_Y = 0;
            return true;
        }
        return false;
        }
        @Override
        public Object next() 
        {
            if(this.hasNext())
            {
                if(names[index_X][index_Y] == "-")
                {
                    index_Y++;
                   if(this.hasNext())  return names[index_X][index_Y++];
                }
               return names[index_X][index_Y++];
            }
           return null;
        }
    }

}
