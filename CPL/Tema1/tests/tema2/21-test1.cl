class A {
    x : SELF_TYPE <- new A;
    y : SELF_TYPE <- new B;
};

class B inherits A {

    a : A <- new SELF_TYPE;
    b : SELF_TYPE <- new A; -- eroare pt ca A nu e conform cu B (adica ~(A <= B) ), ci B e conform cu A
    c : SELF_TYPE <- new B;
};