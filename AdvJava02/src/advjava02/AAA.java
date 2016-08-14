package advjava02;

class AAA {
    TellOMatic tom = TellOMatic.create("initializer of AAA");
    
    AAA(){
        TellOMatic.create("constructor of AAA");
        //checkTom();
    }
    
    void checkTom(){
        System.out.println("tom in AAA is: " + (tom==null? "null" : "not null") );
    }
}

class BBB extends AAA {//implements III{
    TellOMatic tom = TellOMatic.create("initializer of BBB");
    //{ tom = TM; }
    
    BBB(){
        super();
        TellOMatic.create("constructor of BBB");
        //checkTom();
    }
    @Override
    void checkTom(){
        super.checkTom();
        System.out.println("tom in BBB is: " + (tom==null? "null" : "not null") );
    }
}

interface III {
    TellOMatic TM = new TellOMatic("Whatt");
}
