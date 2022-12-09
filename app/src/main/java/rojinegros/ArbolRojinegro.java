package rojinegros;

import lombok.Getter;
import lombok.Setter;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedList;
import java.util.Queue;

public class ArbolRojinegro {
    @Getter
    @Setter
    private ArbolRojinegro izq;

    @Getter
    @Setter
    private ArbolRojinegro der;

    @Getter
    @Setter
    private int valor;

    @Getter
    @Setter
    private boolean black; //Si es negro True, en otro caso rojo

    @Getter
    @Setter
    private ArbolRojinegro father;

    public ArbolRojinegro(ArbolRojinegro izq,
                          ArbolRojinegro der,
                          int valor,
                          boolean black) {
        this.izq = izq;
        this.der = der;
        this.valor = valor;
        this.black = black;
    }

    public ArbolRojinegro() {
        this.izq = null;
        this.der = null;
        this.black = true;
    }
    /*
     * Metodos a implementar
     */

  /*  public void insertFixUp (int x) {


         ArbolRojinegro nodoActual = search(x);


         while (nodoActual.getFather().isBlack() ==false) {

             if (nodoActual.getFather() == null) {

                 nodoActual.setBlack(true);


             }else {

                 if (nodoActual.getFather().getFather() != null) {  //si el abuelo NO es nulo

                     if (nodoActual.getFather() == nodoActual.getFather().getFather().getIzq()) { //si el papa es hijo izuqierdo

                        ArbolRojinegro y = nodoActual.getFather().getFather().getDer();

                        if(y.isBlack() == false){ //si el tio es rojo CASO 1

                            y.setBlack(true);

                            nodoActual.getFather().setBlack(true);

                            nodoActual.getFather().getFather().setBlack(false);

                            nodoActual = nodoActual.getFather();
                        } else {  //si el tio es NEGRO CASO 2 Y 3
                            //por el if anterior yo se que en este punto mi papa es hijo izquierdo de mi abuelo
                            if(nodoActual == nodoActual.getFather().getIzq()){ //si soy el hijo izquierdo de mi papa

                                //entra en el caso 3 LINEAS IZQUIERDAS

                                this.rotacionDerecha(nodoActual.getFather().getFather().getValor());

                                nodoActual = nodoActual.getFather().getFather();

                            }








                        }



                     }




                 }





             }


         }

















//otro posible metodo

        ArbolRojinegro z = search(x);


        while (z.getFather().isBlack() == false) {

            if (z.getFather() == z.getFather().getFather().getIzq()) {

                ArbolRojinegro y = z.getFather().getFather().getDer();

                if( y != null) {

                    if (y.isBlack() == false) {

                        z.getFather().setBlack(true);
                        y.setBlack(true);
                        z.getFather().getFather().setBlack(false);
                        z = z.getFather().getFather();

                    } else {

                        if (z == z.getFather().getDer()) {
                            z = z.getFather();
                            z.rotacionIzquierda(z.getValor()); //si falla camnbiar z por this

                        }
                        z.getFather().setBlack(true);
                        z.getFather().getFather().setBlack(false);
                        z.rotacionDerecha(z.getFather().getFather().getValor());//si falla camnbiar z por this

                    }
                }

            }else {

                ArbolRojinegro y = z.getFather().getFather().getIzq();

                if( y != null) {

                    if (y.isBlack() == false) {

                        z.getFather().setBlack(true);
                        y.setBlack(true);
                        z.getFather().getFather().setBlack(false);
                        z = z.getFather().getFather();

                    } else {

                        if (z == z.getFather().getIzq()) {
                            z = z.getFather();
                            z.rotacionDerecha(z.getValor()); //si falla camnbiar z por this

                        }
                        z.getFather().setBlack(true);
                        z.getFather().getFather().setBlack(false);
                        z.rotacionIzquierda(z.getFather().getFather().getValor());//si falla camnbiar z por this

                    }
                }
            }
        }

        this.setBlack(true);



    }

    */

    public void insert(int value) {
        System.out.println("el valor apenas entrar es" + this.valor);

        if (this.getFather() == null && this.valor == 0) {

            this.setValor(value);
            this.black = true;

        }else {

            if (value >= this.valor) {
                if (this.der == null) {
                    this.der = new ArbolRojinegro(null, null, value, false);
                    this.der.setFather(this);
                  //  insertFixUp(value);


                } else {
                    this.der.insert(value);
                }
            } else {
                if (this.izq == null) {
                    this.izq = new ArbolRojinegro(null, null, value, false);
                    this.izq.setFather(this);
               //     insertFixUp(value);
                } else {
                    this.izq.insert(value);
                }
            }
        }

    }



    public void insertar(int x)  {

        insert(x);

    }


    public int maximo()  {

        if (this.getDer() != null) {
            return this.getDer().maximo();
        } else {
            return this.valor;
        }
    }

    public int minimo()  {
        if (this.getIzq() != null){
            return this.getIzq().minimo();
        } else {
            return this.valor;
        }
    }

    public ArbolRojinegro search(int x) {
        if (this.valor == x) {
            System.out.println("valor de retorno" + this.valor);
            return this;
        } else {
            if (x >= this.valor) {
                System.out.println(this.valor);
                if (this.getDer() != null) {
                    return this.getDer().search(x);
                } else {
                    System.out.println(this.getValor());
                    return null;
                }
            } else {
                if (this.getIzq() != null) {
                    System.out.println("etnro a izquierda de verdad");
                    return this.getIzq().search(x);
                } else {
                    System.out.println("entro a iziquierda null" + this.getValor());
                    return  null;
                }
            }
        }
    }

    public void rotacionIzquierda(int x) {


        ArbolRojinegro nodoActual = search(x);
        //   ArbolRojinegro nodoCambio = search(x);
        ArbolRojinegro y = nodoActual.getDer();
        ArbolRojinegro nuevo = new ArbolRojinegro();


        if(nodoActual.getFather() != null) {

            ArbolRojinegro abuelo = nodoActual.getFather();


            if (y != null) {

                if (abuelo.getIzq() == nodoActual) {


                    y.setFather(abuelo);
                    nodoActual.setFather(y);
                    nodoActual.setDer(y.getIzq());
                    y.setIzq(nodoActual);
                    abuelo.setIzq(y);


                } else {

                    if (abuelo.getDer() == nodoActual) {


                        y.setFather(abuelo);
                        nodoActual.setFather(y);
                        nodoActual.setDer(y.getIzq());
                        y.setIzq(nodoActual);
                        abuelo.setDer(y);

                    }

                }


            }

        }else{

            if(y != null) {



                //    nuevo.setValor();

                System.out.println("entra a nulo rotacion izquierda");

                // nodoCambio.setValor(y.getValor());


                ArbolRojinegro nodoCopia = new ArbolRojinegro(nodoActual.getIzq(), nodoActual.getDer(), nodoActual.getValor(), nodoActual.isBlack());
                nuevo.setValor(y.getValor());
                nuevo.setDer(y.getDer());
                nodoCopia.getDer().setFather(null);
                nodoCopia.setFather(nodoCopia.getDer());
                nodoCopia.setDer(y.getIzq());
                nuevo.setIzq(nodoCopia);
                nuevo.getIzq().getDer().setFather(nuevo.getIzq());

                System.out.println("ARBOL NUEVO CON VALORES: "+nuevo.bfs());

                nodoActual.setFather(nuevo.getFather());
                nodoActual.setValor(nuevo.getValor());
                nodoActual.setDer(nuevo.getDer());
                nodoActual.setIzq(nodoCopia);


            }





        }
    }

    public void rotacionDerecha(int x) {

        ArbolRojinegro nodoActual = search(x);
        //   ArbolRojinegro nodoCambio = search(x);
        ArbolRojinegro y = nodoActual.getIzq();
        ArbolRojinegro nuevo = new ArbolRojinegro();


        if(nodoActual.getFather() != null) {

            ArbolRojinegro abuelo = nodoActual.getFather();


            if (y != null) {

                if (abuelo.getIzq() == nodoActual) {


                    y.setFather(abuelo);
                    nodoActual.setFather(y);
                    nodoActual.setIzq(y.getDer());
                    y.setDer(nodoActual);
                    abuelo.setIzq(y);


                } else {

                    if (abuelo.getDer() == nodoActual) {


                        y.setFather(abuelo);
                        nodoActual.setFather(y);
                        nodoActual.setIzq(y.getDer());
                        y.setDer(nodoActual);
                        abuelo.setDer(y);

                    }

                }


            }

        }else{

            if(y != null) {



                //    nuevo.setValor();

                System.out.println("entra a nulo rotacion izquierda");

                // nodoCambio.setValor(y.getValor());


                ArbolRojinegro nodoCopia = new ArbolRojinegro(nodoActual.getIzq(), nodoActual.getDer(), nodoActual.getValor(), nodoActual.isBlack());
                nuevo.setValor(y.getValor());
                nuevo.setIzq(y.getIzq());
                nodoCopia.getIzq().setFather(null);
                nodoCopia.setFather(nodoCopia.getIzq());
                nodoCopia.setIzq(y.getDer());
                nuevo.setDer(nodoCopia);
                nuevo.getDer().getIzq().setFather(nuevo.getDer());

                System.out.println("ARBOL NUEVO CON VALORES: "+nuevo.bfs());

                nodoActual.setFather(nuevo.getFather());
                nodoActual.setValor(nuevo.getValor());
                nodoActual.setIzq(nuevo.getIzq());
                nodoActual.setDer(nodoCopia);



            }





        }



    }
    public String bfs() {
        String salida = "";
        String separador = "";
        Queue<ArbolRojinegro> cola = new LinkedList<>();
        cola.add(this);
        while (cola.size() > 0) {
            ArbolRojinegro nodo = cola.poll();
            //   System.out.println("el valor del nodo es: "+ nodo.getValor());
            salida += separador + String.valueOf(nodo.getValor());
            separador = " ";
            if (nodo.getIzq() != null) {
                cola.add(nodo.getIzq());
            }
            if (nodo.getDer() != null) {
                cola.add(nodo.getDer());
            }
        }
        return salida;
    }


    /*
     * Recorrido inorder.
     * Verifica propiedad de orden.
     */
    public String inorden() {
        String recorrido = "";
        String separador = "";
        if (this.getIzq() != null) {
            recorrido += this.getIzq().inorden();
            separador = " ";
        }
        recorrido += separador + String.valueOf(this.getValor());
        if (this.getDer() != null) {
            recorrido += " " + this.getDer().inorden();
        }
        return recorrido;
    }

}