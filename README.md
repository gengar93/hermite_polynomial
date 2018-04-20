# Hermite Polynomials

Hermite Polynomials appear in several places in physics. They are a sequence of polynomials and are used as an orthogonal basis set to express functions. 

The first few of them are given below. 

![first few polynomials](https://i.imgur.com/v1OjsXC.jpg)

Given a hermite polynomial, the next hermite polynimial is given by the recursive relation

![hermite polynomial recursive relation](https://i.imgur.com/swBLKPJ.jpg)

Since generating polynomials involves differentiation and multiplication with $2x$, both of which are linear operators, the the polynomials and the operators themselves can be expressed as matrices. 

For example,

![hermite polynomials as matrices](https://i.imgur.com/2vCxepf.jpg)

As can be easily verified, the matrix representation of the operator to generate the next polynomial is 

![matrix to generate next polynomial](https://i.imgur.com/xfm5BJM.jpg)

Multiplying this matrix with a polynomial (also represented as a matrix) gives the matrix representing the next polynomial.

To compute and store larger polynomials, larger matrices are required. 

**The Java program contains a class to create polynomial type objects, a class to create, add and multiply matrices and a class to create hermite polynomials by specifying the number.**

**The Jupyter Notebook file shows how the matrix operators to generate the next polynomial is pretty sparse but operators that generate the +10 polynomial for example is nearly half full.**
