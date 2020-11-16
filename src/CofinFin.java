import java.util.*;

public class CofinFin {

    /// THIS IS THE TEMPLATE YOU SHOULD USE FOR THE TEAM PROJECT
    /*


Team members: Brook Vail, Casey Pyburn, Jack O'Connor, Jason Means



Replace the stub code for each of the constructors and methods given below with
correct code.


     */
    private static final long
    RAND_SEED = 0L;

    private static final Random
    rng = new Random(RAND_SEED);

    private static final int
    Max = Integer.MAX_VALUE,
    MaxDiv2 = Max/2;

    private static CofinFin emT;

    private boolean isComplemented;
    private TreeSet<Integer> theSet; // should never be null, even if
    // the set is empty

    /*

     This class implements an instance of a subset of the natural numbers,
     { 0, 1, 2, ... }, that is either finite or cofinite(that is, its complement
     with respect to the natural numbers is theSet) according to the following
     scheme.

     There are two mutually exclusive cases

     Case I: the represented set is finite

     isComplemented is false

     theSet has exactly the members of the set which are ordered ascending in
     the tree.

     Examples

     1. empty set 
        isComplemented is false 
        theSet is empty, BUT NOT NULL

     2. { 0, 2, 4 }
        isComplemented is false
        theSet has the values { 0, 2, 4 }


     Case II: the represented is cofinite

     isComplemented is true and theSet has all the values that are NOT in the
     represented value.

     Below we use N to stand for the set of natural numbers { 0, 1, 2, 3, ... }

     Examples

     1. N = ( 0, 1, 2, 3, ... }
        isComplemented is true
        theSet is empty, BUT NOT NULL

     2. { 1, 3, 5, 6, 7, ... }
        isComplemented is true
        theSet has the values { 0, 2, 4 }

     class invariant:
        theSet is not null and never contains a value < 0

     Constructors should establish the class invariant and mutators 
     should preserve it.

     */

    // constructors

    // I WILL CODE THIS ONE SO I CAN USE IT BELOW
    public CofinFin() {
        // constructs the rep of the empty set;
        // isComplemented is initialized to false by default
        theSet = new TreeSet<Integer>();

    }

    public CofinFin(boolean comp, int n) {
        /*

         if  n >= 0
            if comp is false
               constructs the rep of { n }
             else
            constructs the rep of the complement of { n }

         if n < 0
            if comp is false
               creates the rep of the empty set 
            else
               creates the rep of N

         basically, ignore n if it's not >= 0

         YOU NEED TO CODE THIS

         */

        this.isComplemented = comp;
        this.theSet = new TreeSet<Integer>();
        if(n >= 0) {

            this.theSet.add(n);

        }




    }   

    //this();


    public CofinFin(boolean comp, int[] A) {
        /*
         if comp is false
            if A is null 
               constructs the rep of the empty set 
            else
               constructs the rep of the distinct values in A that are >= 0 (could be
               none, e.g., if A is { -1,-2,-3 }) 
         else 
            if A is null 
               constructs the rep of all of the natural numbers 
            else
               constructs the rep of the complement of all of the natural 
               numbers (which are all >= 0) that are elements of A

         YOU NEED TO CODE THIS
         */
        this.isComplemented = comp;
        this.theSet = new TreeSet<Integer>();

        if (A != null){
            for (int n : A)
               if (n >= 0)
                  this.theSet.add(n);
         }
    }


    // mutators

    public void remove(int n) {
        /*
       if n < 0 or n is not in the set represented by this, no changes are made
       else, the rep is modified to reflect the removal of n from the set
       this represents
      /*
         YOU NEED TO CODE THIS
         */
        if(n>=0) {          
            if(this.isComplemented)
                this.theSet.add(n);
            else
                this.theSet.remove(n);

        }

        //for any n >= 0 and CofinFin object S

        //n is in the value that S represents iff   S.theSet.contains(n) != S.isComplemented

    }

    public void add(int n) {
        /*

         if n < 0 or n is in the set represented by this, no changes are made
         else, the rep is modified to reflect the addition of n to the set
         this represents

         YOU NEED TO CODE THIS
         */

        if(n >= 0) {
            if(this.isComplemented)
                this.theSet.remove(n);
            else 
                this.theSet.add(n);
        }
    }

    // operations

    public CofinFin union(CofinFin other) throws Exception {

        /*

       Error checking:

       1. checks that other is not null; if it is, it throws an exception with the 
          message

          "Invalid call to union: other is null"


       otherwise it creates and returns a new value that represents the union of this and
       other;

       this and other are NOT modified and there are no shared data members between the 
       new object and the operands.  Note, it is possible that this == other, as in 

       CofinFin A,C;

       A = new CofinFin();
       C = A.union(A);


       YOU NEED TO CODE THIS

         */
        CofinFin resultA = new CofinFin();
        TreeSet<Integer> result = new TreeSet<Integer>();

        if(other == null) 

            throw new Exception ("Invalid call to union: other is null");
        else {
            resultA.isComplemented = (this.isComplemented || other.isComplemented);
            if(this.isComplemented == false && other.isComplemented == false) {          
                result.addAll(this.theSet);           
                result.addAll(other.theSet);
            }
            else if (this.isComplemented == false && other.isComplemented == true) {
                result.addAll(other.theSet);           
                result.removeAll(this.theSet);
            }
            else if (this.isComplemented == true && other.isComplemented == false)
            {
                result.addAll(this.theSet);
                result.removeAll(other.theSet);
            }
            else if (this.isComplemented == true && other.isComplemented == true) {
                result.addAll(other.theSet);
                result.retainAll(this.theSet);         
            }
            resultA.theSet.addAll(result);

        }
        return resultA;

    }

    public CofinFin intersect(CofinFin other) throws Exception {
        /*

       Error checking:

       1. checks that other is not null; if it is, it throws an exception with the 
          message

          "Invalid call to intersect: other is null"


       otherwise it creates and returns a new value that represents the intersection
       of this and other;

       this and other are NOT modified and there are no shared data members between the 
       new object and the operands.  Note, it is possible that this == other, as in 

       CofinFin A,C;

       A = new CofinFin();
       C = A.intersect(A);


       YOU NEED TO CODE THIS

         */
        CofinFin resultA = new CofinFin();
        TreeSet<Integer> result = new TreeSet<Integer>();

        if(other == null) 

            throw new Exception ("Invalid call to intersect: other is null");
        else {
            resultA.isComplemented = (this.isComplemented && other.isComplemented);
            if(this.isComplemented == false && other.isComplemented == false) {          
                result.addAll(this.theSet);           
                result.retainAll(other.theSet);
            }
            else if (this.isComplemented == false && other.isComplemented == true) {
                result.addAll(this.theSet);           
                result.removeAll(other.theSet);
            }
            else if (this.isComplemented == true && other.isComplemented == false)
            {
                result.addAll(other.theSet);
                result.removeAll(this.theSet);
            }
            else if (this.isComplemented == true && other.isComplemented == true) {
                result.addAll(other.theSet);
                result.addAll(this.theSet);         
            }
            resultA.theSet.addAll(result);

        }
        return resultA;

    }

    public CofinFin complement() {
        /*


       creates and returns a new value that represents the complement of this;
       this is NOT modified;


       YOU NEED TO CODE THIS


         */

        CofinFin resultA = new CofinFin();
        resultA.theSet.addAll(this.theSet);

        if(this.isComplemented==false)
            resultA.isComplemented = true;
        else 
            resultA.isComplemented = false;

        return resultA;
    }

    public boolean isIn(int n) {
        /*


       returns false if n < 0 or n is not in the set
          represented by this
       else returns true

       this is not modified;


         YOU NEED TO CODE THIS

         */


        if (isComplemented == false) {

            if (n < 0 || !this.theSet.contains(n)) {
               return false;
            } else {
               return true;
            }
         } else {
            if (n >= 0 && !this.theSet.contains(n)) {
               return true;
            } else {
               return false;
            }
         }



    }


    public String toString() {
        /*
         If isComplemented is false if the set is empty returns the string "{}" else
         returns the string "{ v1, v2, ... , vk }" where the vi are the distinct
         k values in the set sorted in increasing order For example, { 0, 2, 3 }
         should return the string "{ 0, 2, 3 }" else returns the string CMPx
         where x is the string for the set if isComplemented were false, for
         example, all the natural numbers would have the string "CMP{}", the set
         that is all natural numbers except { 1, 3, 5 }, would have the string
         "CMP{ 1, 3, 5 }", etc.

         */

        boolean notEmpt = !theSet.isEmpty();

        StringBuffer bf = new StringBuffer();

        if (isComplemented)
            bf.append("CMP");

        bf.append('{');
        if (notEmpt){
            bf.append(' ');

            Iterator<Integer> iter = theSet.iterator();

            while (iter.hasNext()) {
                bf.append(iter.next().toString());
                if (iter.hasNext())
                    bf.append(", ");
            }
            bf.append(' ');
        }

        bf.append('}');

        return bf.toString();
    }

    public boolean equals(CofinFin other) throws Exception {
        /*

       Error checking:

       1. checks that other is not null; if it is, it throws an exception with the 
          message

          "Invalid call to equals: other is null"


       otherwise it returns true when the isComplemented data members are the same and the
       theSet data members are equal as sets, else false

       creates and returns a new value that represents the intersection
       of this and other;

       Hint: use the equals method for TreeSet.

       YOU NEED TO CODE THIS

         */


        if (other == null)

            throw new Exception("Invalid call to equals: other is null");

         else {
            if (this.isComplemented == other.isComplemented) {
               if (this.theSet.equals(other.theSet)) {
                  return true;
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }

    }


    public boolean isSubsetOf(CofinFin other) throws Exception {
        /*

       Error checking:

       1. checks that other is not null; if it is, it throws an exception with the 
          message

          "Invalid call to isSubsetOf: other is null"


       otherwise it returns true when the set represented by this is a subset
       of the set represented by, and false if it is not a subset of the set
       represented by other.

       YOU NEED TO CODE THIS


       Hint: do a case by case analysis

         this        other              what do you need to test

         finite      finite
         finite      cofinite
         cofinite    finite
         cofinite    cofinite

         */

        if (other == null) {
            throw new Exception("Invalid call to isSubsetOf: other is null");
         } else {
            if (this.isComplemented == false && other.isComplemented == false) { // finite finite
               if (other.theSet.containsAll(this.theSet)) { // returns true if other has all elements of this
                  return true;
               } else {
                  return false;
               }
            } else if (this.isComplemented == false && other.isComplemented == true) { // finite cofinite

               if (!Collections.disjoint(other.theSet, this.theSet)) { // returns true if any common elements between other
                                                                       // and this
                  return false;
               } else {
                  return true;
               }

            } else if (this.isComplemented == true && other.isComplemented == true) { // cofinite cofinite
               if (this.theSet.containsAll(other.theSet)) { // returns true if this has all elements of other
                  return true;
               } else {
                  return false;
               }
            } else {
               return false; // cofinite finite -- cofinite can't be a subset of a finite set
            }

         }

    }


    public int maximum() throws Exception{
        /*

      if this is cofinite
         throws an exception with the message 
         "Invalid call to maximum: receiver is cofinite"
      else if this is finite and empty
         throws an exception with the message 
         "Invalid call to maximum: receiver is empty"
      else // this is finite and not empty
         returns the maximum value in this

         YOU NEED TO CODE THIS

         */
        if (this.isComplemented == true) {
            throw new Exception("Invalid call to maximum: receiver is cofinite");
         } else if (this.theSet.isEmpty()) {
            throw new Exception("Invalid call to maximum: receiver is empty");
         } else {
            return this.theSet.last();
         }


    }

    public int minimum() throws Exception{
        /*
         YOU NEED TO CODE THIS
         */
        /*

      if this represents the empty set
         throws an exception with the message 
         "Invalid call to minimum: receiver is empty"
      else // this is not empty
         returns the minimum value in the set represented by this

         */
        if (this.isComplemented == false) {
            if (this.theSet.isEmpty()) {
               throw new Exception("Invalid call to minimum: receiver is empty");
            } else {
               return this.theSet.first();
            }
         } else {
            if (this.theSet.isEmpty()) {
               return 0;
            } else {
               for (int i = 0; i < this.theSet.last() - 1; i++) {
                  if (!this.theSet.contains(i)) {
                     return i;
                  }

               }
               return this.theSet.last() + 1;
            }

         }
    }
    public CofinFin setDifference(CofinFin other)throws Exception{
        
        /***

           Creates a new CF object and loads it with the
           result of subtracting of the members of the set represented by other
           from the set represented by this.
           
           There is one error condition, other is null.  If that is the case,
           the method should throw an exception with the message
           
           "Invalid call to setDifference: other is null"



            YOU MUST CODE THIS YOURSELF AS AN INDIVIDUAL


        ***/
            
        CofinFin resultA = new CofinFin();
            TreeSet<Integer> result = new TreeSet<Integer>();

            if(other == null) 

                throw new Exception ("Invalid call to setDifference: other is null");
            else {
                resultA.isComplemented = (this.isComplemented && !other.isComplemented);
                if(this.isComplemented == false && other.isComplemented == false) {          
                    result.addAll(this.theSet);           
                    result.removeAll(other.theSet);
                }
                else if (this.isComplemented == false && other.isComplemented == true) {
                    result.addAll(this.theSet);           
                    result.retainAll(other.theSet);
                }
                else if (this.isComplemented == true && other.isComplemented == false)
                {
                    result.addAll(other.theSet);
                    result.addAll(this.theSet);
                }
                else if (this.isComplemented == true && other.isComplemented == true) {
                    result.addAll(other.theSet);
                    result.removeAll(this.theSet);         
                }
                resultA.theSet.addAll(result);

            }
            return resultA;

    }


        public CofinFin symmetricDifference(CofinFin other)throws Exception{
      
        /***

           Creates a new CF object and loads it with the
           symmetric difference of  the set represented by other
           and the set represented by this.
           
           There is one error condition, other is null.  If that is the case,
           the method should throw an exception with the message
           
           "Invalid call to symmetricDifference: other is null"



            YOU MUST CODE THIS YOURSELF AS AN INDIVIDUAL


        ***/
            
            CofinFin resultA = new CofinFin();
            TreeSet<Integer> result = new TreeSet<Integer>();

            if(other == null) 

                throw new Exception ("Invalid call to symmetricDifference: other is null");
            else {
                resultA.isComplemented = (this.isComplemented != other.isComplemented);
                
                    TreeSet<Integer> temp1 = new TreeSet<Integer>();
                    temp1.addAll(this.theSet);           
                    temp1.retainAll(other.theSet); //intersection 
                    
                    result.addAll(this.theSet);
                    result.addAll(other.theSet);  //union
                    
                    result.removeAll(temp1);  // remove the intersection from union leaves the symmetric diff
                    
                resultA.theSet.addAll(result);

            }
            return resultA;

    }
    private static  // so they will all be initialized to null
    CofinFin
    // I don't use l for an identifier
    a,b,c,d,e,f,g,h,i,j,k,m,n,o,p,q,r,s,t,u,v,w,x,y,z,

    aa, ab, ac, ad, ae, af, ag, ah, ai, aj,ak, al, am, an,
    ao, ap, aq, ar, as, at, au, av, aw, ax, ay, az,

    ba, bb, bc, bd, be, bf, bg, bh, bi, bj, bk, bl, bm, bn,
    bo, bp, bq, br, bs, bt, bu, bv, bw, bx, by, bz,

    cc, dd, ee,

    newA, newB, newC;  // 25 + 52 + 6 = 83 variables





    //  the driver tests the methods
    public static void main (String[] args)throws Exception{

        boolean res;

        int ii, jj, kk;

        int [][] 
                arrayConstants = {
                        { -1, -2, -3, -4},
                        { 0, 1, 2, 3},
                        {-1, 0, -2, 1, -3, 2, -4}, 
                        { 2, 3, 5, 7 },
                        { 1, 3, 5, 7 },
                        { 1, 2, 3, 4 },
                        { 0, 1, 5, 6, 7, 10, 11, 13},
                        { 2, 3, 6, 8, 9 },
                        { 2, 4, 6, 8, 10, 13 },
                        { 13, 15, 17, 19, 21},
                        { 3, 5, 7, 11},
                        { 1, 2, 3, 7 },
                        { 10, 30, 50, 70},
                        { 10, 20, 30, 70  },
                        { 0, 1, 4, 5, 6, 7, 9},
                        { 0, 1, 4, 5, 6, 7, 9, 10},
                        { 8, 9, 11, 12 },
                        { 0, 1, 4, 5, 6, 7, 8, 10, 11, 12},
                        {100, 200, 300, 400, 500, 1000},
                        { 0, 2, 4, 6, 8, 10 ,12, 14 },
                        { 1, 3, 5, 7, 9, 11, 13, 15, 17 },
                        new int[0],
                        null,
                        { -1 },
                        { 0 },
                        { 100 },
                        {}
        };


        CofinFin[]
                fromStaticVariables = {

                        a,b,c,d,e,f,g,h,i,j,k,m,n,o,p,q,r,s,t,u,v,w,x,y,z,

                        aa, ab, ac, ad, ae, af, ag, ah, ai, aj,ak, al, am, an,
                        ao, ap, aq, ar, as, at, au, av, aw, ax, ay, az,

                        ba, bb, bc, bd, be, bf, bg, bh, bi, bj, bk, bl, bm, bn,
                        bo, bp, bq, br, bs, bt, bu, bv, bw, bx, by, bz,

                        cc, dd, ee,

                        newA, newB, newC} ,
                // 25 + 52 + 6 = 83 variables

                allTestCases = new CofinFin[arrayConstants.length * 2 + fromStaticVariables.length];


        ////////////////  SIMPLE CONSTRUCTOR TESTS

        System.out.println("Simple Constructor Tests.\n");

        a = new CofinFin(false, -1);
        System.out.println("a = " + a);
        b = new CofinFin(true, -2);
        System.out.println("b = " + b);
        c = new CofinFin(false, 1000);
        System.out.println("c = " + c);
        d = new CofinFin(true,1234);
        System.out.println("d = " + d);
        e = new CofinFin(false, 0);
        System.out.println("e = " + e);
        f = new CofinFin(true, 0);
        System.out.println("f = " + f);
        g = new CofinFin(false, 1);
        System.out.println("g = " + g);
        h = new CofinFin(true, 1);
        System.out.println("h = " + h);
        i = new CofinFin(false, Integer.MAX_VALUE);
        System.out.println("i = " + i);



        ////////////////  ARRAY CONSTRUCTOR TESTS

        System.out.println("\nArray Constructor Tests\n");

        for (int i = 0; i < arrayConstants.length * 2; i++){
            if (i < arrayConstants.length)
                allTestCases[i] = new CofinFin(false, arrayConstants[i]);
            else
                allTestCases[i] = new CofinFin(true, arrayConstants[i - arrayConstants.length]);
            System.out.println("i = " + i + " CofinFin is " + allTestCases[i]);
        }



        ////////////////  ISIN REMOVE AND ADD TESTS


        System.out.println("\nisIn and remove and add Tests\n");

        for (int i = 0; i < arrayConstants.length * 2; i++){
            CofinFin temp = allTestCases[i];
            boolean
            isIn0 = temp.isIn(0),
            isIn1 = temp.isIn(1),
            isInMaxDiv2 = temp.isIn(MaxDiv2),
            isInMax = temp.isIn(Max);

            System.out.println("The four isIn results are " + isIn0 + " " + isIn1
                    + " " + isInMaxDiv2 + " " + isInMax);


            temp.remove(-1);   
            temp.remove(0);
            temp.remove(1);
            temp.remove(MaxDiv2);
            temp.remove(Max);
            System.out.println("i = " + i + " after the four removes, temp is " + temp);

            // restore prior value
            if (isIn0)
                temp.add(0);
            if (isIn1)
                temp.add(1);
            if (isInMaxDiv2)
                temp.add(MaxDiv2);
            if (isInMax)
                temp.add(Max);

            System.out.println("After restoration, value is " + temp);
        }



        ////////////////  MORE ADD TESTS

        // construct a few explicitly
        j = new CofinFin();
        j.isComplemented = false;
        j.theSet.add(1000);

        k = new CofinFin();
        k.isComplemented = true;
        k.theSet.add(1000);

        m = new CofinFin();
        m.isComplemented = true;
        m. theSet.add(500);

        j.add(-1);
        k.add(-2);
        m.add(Integer.MIN_VALUE);

        System.out.println("j = " + j.toString());
        System.out.println("k = " + k.toString());
        System.out.println("m = " + m.toString());





        System.out.println("\nAdd Tests\n");


        j = new CofinFin(true, new int[] { 1,2,3 });
        j.add(-1);
        j.add(Integer.MIN_VALUE);
        System.out.println("After adding -1 and " + Integer.MIN_VALUE +
                " to j, j = " + j.toString());

        for (ii = arrayConstants.length * 2; ii < allTestCases.length; ii++){
            if (allTestCases[ii] == null){  // create a new instance
                int size = rng.nextInt(10);
                allTestCases[ii] = new CofinFin(rng.nextBoolean(), null);
                for (int v = 0; v < size; v++)
                    allTestCases[ii].add(rng.nextInt(Max));
            }
            // make a copy of the current value
            CofinFin tc = new CofinFin();
            tc.isComplemented = allTestCases[ii].isComplemented;
            tc.theSet.addAll(allTestCases[ii].theSet);

            System.out.println("Testing add for " + tc.toString());
            tc.add(-2);
            System.out.println("After adding -2, new value is " + tc.toString());
            tc.add(Integer.MIN_VALUE);
            System.out.println("After adding " + Integer.MIN_VALUE + ", new value is " + tc.toString());

            for (jj = 0; jj < 100; ){
                tc.add(jj);
                System.out.println("After adding " + jj + ", new value is " + tc.toString());
                jj = 2 * jj + 1;
            }

        }



        ////////////////   UNION TESTS


        System.out.println("\nUnion Tests\n");

        for (int i = 0; i < allTestCases.length; i++){
            CofinFin outer = allTestCases[i];
            for (int j = 0; j < allTestCases.length; j++) {
                System.out.println(" (i,j) = (" + i + ", " + j + ") result = " +
                        outer.union(allTestCases[j]));
                //System.out.println(outer+", "+allTestCases[j]);
            }
        }


        // test exception
        try{
            CofinFin xxx = allTestCases[0].union(null);
            System.out.println("result is " + xxx);
        }
        catch(Exception e){
            System.out.println("Union with null other threw an exception with message '" 
                    + e.getMessage() + "'");
        }

        ////////////////////  INTERSECTION TESTS


        System.out.println("\nIntersection Tests\n");


        for (int i = 0; i < allTestCases.length; i++){
            CofinFin outer = allTestCases[i];
            for (int j = 0; j < allTestCases.length; j++)
                System.out.println(" (i,j) = (" + i + ", " + j + ") result = " +
                        outer.intersect(allTestCases[j]));
        }

        // test exception
        try{
            CofinFin xxx = allTestCases[0].intersect(null);
            System.out.println("result is " + xxx);
        }
        catch(Exception e){
            System.out.println("Intersection with null other threw an exception with message '" 
                    + e.getMessage()+ "'");
        }



        ////////////////////  COMPLEMENT TESTS

        System.out.println("\nComplement Tests\n");


        for (int i = 0; i < allTestCases.length; i++)
            System.out.println("i = " + i + " result = " +
                    allTestCases[i].complement());



        // test that complement does not share the Set data member
        j = new CofinFin(false, new int[]{1,2,3});
        m = j.complement();
        m.add(1);
        m.remove(4);
        System.out.println("after adding 1 to m and removing 4, j is " + j);
        j = new CofinFin(true, new int[]{1,2,3});
        m = j.complement();
        m.remove(1);
        m.add(4);
        System.out.println("after adding 4 to m and removing 1, j is " + j);




        ///////////////// IS IN TESTS


        System.out.println("\nisIn Tests\n");

        for (int i = 0; i < allTestCases.length; i++){
            CofinFin outer = allTestCases[i];
            for (int j = 0; j < 50; j++)
                System.out.println(" (i,j) = (" + i + ", " + j + ") isIn result = " +
                        outer.isIn(j));
        }



        ///////////////// SUBSET TESTS

        System.out.println("\nSubset Tests\n");


        for (int i = 0; i < allTestCases.length; i++){
            CofinFin outer = allTestCases[i];
            for (int j = 0; j < allTestCases.length; j++)
                System.out.println(" (i,j) = (" + i + ", " + j + ") subset result = " +
                        outer.isSubsetOf(allTestCases[j]));
        }

        // test exception
        emT = new CofinFin(false, null);
        try{

            System.out.println("Result for empty set is subset of null = " + emT.isSubsetOf(null));

        }
        catch (Exception ee){
            System.out.println("Call to isSubsetOf with null other threw an exception with message '" 
                    + ee.getMessage() + "'");
        }




        ///////////////// EQUALS  TESTS

        System.out.println("\nEquals Tests\n");



        for (int i = 0; i < allTestCases.length; i++){
            CofinFin outer = allTestCases[i];
            for (int j = 0; j < allTestCases.length; j++)
                System.out.println(" (i,j) = (" + i + ", " + j + ") equals result = " +
                        outer.equals(allTestCases[j]));
        }

        // test exception
        try{

            System.out.println("empty set equals null is " + emT.equals(null));

        }
        catch (Exception e){
            System.out.println("Call to equals with null other threw an exception with message '" 
                    + e.getMessage() + "'");
        }

        ///////////////   MINIMUM TESTS

        System.out.println("\nMinimum Tests\n");

        for (ii = 0; ii < allTestCases.length; ii++){
            System.out.println("Minimum test # " + ii);
            try{
                System.out.println("Min value is " + allTestCases[ii].minimum());
            }
            catch (Exception e){
                System.out.println("The call threw an exception with message '" +
                        e.getMessage() + "'");
            }
        }


        ///////////////   MAXMIMUM TESTS

        System.out.println("\nMaximmum Tests\n");

        for (ii = 0; ii < allTestCases.length; ii++){
            System.out.println("Maximum test # " + ii);
            try{
                System.out.println("Max value is " + allTestCases[ii].maximum());
            }
            catch (Exception e){
                System.out.println("The call threw an exception with message '" +
                        e.getMessage() + "'");
            }
        }


        //////////////  TESTS FOR SIDE EFFECTS OF THE OPERATIONS

        System.out.println("\nSide Effect Tests.\n");

        CofinFin
        x1 = new CofinFin(),
        x2 = new CofinFin();

        x1.theSet.add(Integer.MAX_VALUE - 1);
        x2.theSet.add(Integer.MAX_VALUE - 2);
        x2.isComplemented = true;

        for (ii = 0; ii < allTestCases.length; ii++){
            allTestCases[ii].union(x2);
            x2.union(allTestCases[ii]);
            allTestCases[ii].intersect(x1);
            x1.intersect(allTestCases[ii]);
            allTestCases[ii].complement();
            System.out.println("ii = " + ii + " " + allTestCases[ii]);
        }

    }
}



