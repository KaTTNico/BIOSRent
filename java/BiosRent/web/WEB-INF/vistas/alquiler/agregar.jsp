<%-- 
    Document   : agregar
    Created on : Jun 8, 2019, 10:42:13 AM
    Author     : Nicolas
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat" %>  

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra title="Alquiler">
    <jsp:body>
        <fmt:setLocale value="en-US"/>

        <form method='post' accion="agregar" autocomplete="off">
            <div class="autocomplete" style="width:300px;">
                <style type="text/css" scoped>
                    /*the container must be positioned relative:*/
                    .autocomplete {
                        position: relative;
                        display: inline-block;
                    }

                    #cliente,#cantidadDias {
                        border: 1px solid transparent;
                        background-color: #f1f1f1;
                        padding: 10px;
                        font-size: 16px;
                        background-color: #f1f1f1;
                        width: 100%;
                    }

                    .autocomplete-items {
                        position: absolute;
                        border: 1px solid #d4d4d4;
                        border-bottom: none;
                        border-top: none;
                        z-index: 99;
                        /*position the autocomplete items to be the same width as the container:*/
                        top: 100%;
                        left: 0;
                        right: 0;
                    }

                    .autocomplete-items div {
                        padding: 10px;
                        cursor: pointer;
                        background-color: #fff; 
                        border-bottom: 1px solid #d4d4d4; 
                    }

                    /*when hovering an item:*/
                    .autocomplete-items div:hover {
                        background-color: #e9e9e9; 
                    }

                    /*when navigating through the items using the arrow keys:*/
                    .autocomplete-active {
                        background-color: DodgerBlue !important; 
                        color: #ffffff; 
                    }
                </style>
                <input type="hidden" name="sucursal" value="${param.sucursal}"/>
                <label>Vehiculo</label>
                <br />
                <input type="text" value="${param.matricula}" name="matricula" readonly disabled />
                <br /><br />
                <label>Cantidad de dias:</label>
                <input id="cantidadDias" name="cantidadDias" value="" placeholder="Cantidad de dias" type="number" min="0" step="1" />
                <br /><br />
                <label>Contrata seguro:</label>
                <input type="checkbox" name="contratoSeguro" />
                <br /><br />
                <label>Cliente:</label>
                <input id="cliente" type="text" name="cliente" value="" placeholder="Cliente"/>
            </div>
            <br /><br />
            <input type="submit" value="agregar"/>
        </form>
        <p><a href="index">Volver...</a></p>
        <t:mensaje />
        <script>
            function autocomplete(inp, arr) {
                var currentFocus;
                /*agregar el evento para cuando se escriba dentro del input:*/
                inp.addEventListener("input", function (e) {
                    var a, b, i, val = this.value;
                    /*cerrar lista*/
                    closeAllLists();
                    if (!val) {
                        return false;
                    }
                    currentFocus = -1;
                    /*crear lista:*/
                    a = document.createElement("DIV");
                    a.setAttribute("id", this.id + "autocomplete-list");
                    a.setAttribute("class", "autocomplete-items");
                    /*append div como el child del autocomplete container:*/
                    this.parentNode.appendChild(a);

                    for (i = 0; i < arr.clientes.length; i++) {
                        /*chequear coincidencias (si lo que se escribe en el input empieza con algun elemento del JSON clientes)*/
                        if (arr.clientes[i].ci.substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                            /*crear un div para cada elemento que hace match:*/
                            b = document.createElement("DIV");
                            /*marcar en negrita las letras que coinciden con la busqueda ingresada:*/
                            b.innerHTML = "<strong>" + arr.clientes[i].ci.substr(0, val.length) + "</strong>";
                            b.innerHTML += arr.clientes[i].ci.substr(val.length) + " [" + arr.clientes[i].nombreCompleto + "]";
                            /*retener el valor:*/
                            b.innerHTML += "<input type='hidden' value='" + arr.clientes[i].ci + "'>";
                            /*evento click:*/
                            b.addEventListener("click", function (e) {
                                /*insertar valor:*/
                                inp.value = this.getElementsByTagName("input")[0].value;
                                /*ccerrar lista:*/
                                closeAllLists();
                            });
                            a.appendChild(b);
                        }
                    }
                });
                /*evento keydown:*/
                inp.addEventListener("keydown", function (e) {
                    var x = document.getElementById(this.id + "autocomplete-list");
                    if (x)
                        x = x.getElementsByTagName("div");
                    if (e.keyCode == 40) {
                        /*navegabilidad sobre los items con arrow DOWN (keycode40):*/
                        currentFocus++;
                        /*hacer que se vea mas visible el item:*/
                        addActive(x);
                    } else if (e.keyCode == 38) {
                        /*navegabilidad sobre los items con arrow UP (keycode38):*/
                        currentFocus--;
                        /*hacer que se vea mas visible el item:*/
                        addActive(x);
                    } else if (e.keyCode == 13) {
                        /*poner el valor dentro del input si se presiona ENTER (keycode13)*/
                        e.preventDefault();
                        if (currentFocus > -1) {
                            /*simular click*/
                            if (x)
                                x[currentFocus].click();
                        }
                    }
                });
                function addActive(x) {
                    if (!x)
                        return false;
                    /*remover active de items:*/
                    removeActive(x);
                    if (currentFocus >= x.length)
                        currentFocus = 0;
                    if (currentFocus < 0)
                        currentFocus = (x.length - 1);
                    /*add class "autocomplete-active":*/
                    x[currentFocus].classList.add("autocomplete-active");
                }
                function removeActive(x) {
                    /*remover active de items de autocomplete:*/
                    for (var i = 0; i < x.length; i++) {
                        x[i].classList.remove("autocomplete-active");
                    }
                }
                function closeAllLists(elmnt) {
                    /*cerrar todas las listas:*/
                    var x = document.getElementsByClassName("autocomplete-items");
                    for (var i = 0; i < x.length; i++) {
                        if (elmnt != x[i] && elmnt != inp) {
                            x[i].parentNode.removeChild(x[i]);
                        }
                    }
                }
                /*cuando se clickea fuera del input cerrar todas las listas:*/
                document.addEventListener("click", function (e) {
                    closeAllLists(e.target);
                });
            }

            /*inicializar autocomplete en el input cliente mandando el JSON clientes:*/
            autocomplete(document.getElementById("cliente"), ${clientes});
        </script>
    </jsp:body>
</t:paginaMaestra>