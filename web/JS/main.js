var menu = false;
var paginaMostrada = 0;

function funcMenu(idMenu, direccion) {
    if (menu === false) {
        if (idMenu !== paginaMostrada) {
            $.ajax({
                url: 'index/',
                method: 'get',
                data: {
                    pagina: idMenu,
                    movil: 0
                },
                success: function (resultados) {
                    if (resultados !== null && resultados !== '') {
                        url(direccion);
                        pintarAcomodar();
                        respuestaServelet(resultados, idMenu);
                    } else {
                        alert('error fatal');
                    }
                }
            });
        } else {
            return false;
        }
    } else {
        menuAbrir();
        funcMenu(idMenu, direccion);
    }
}

function pintarAcomodar() {
    $('#contenedorPrincipal').attr('class', 'contenedorPrincipal limpiar');
    $('#contenedorPrincipal').css('height', '0px');
    $('#contenedorPrincipal').css('height', 'auto');
    $('#contenedorPrincipal').empty();
}

function respuestaServelet(jsonString, nuevaPagina) {
    document.body.scrollTop = 0; // For Chrome, Safari and Opera
    document.documentElement.scrollTop = 0;
    $('#navMenu-' + paginaMostrada).removeClass('activo');
    paginaMostrada = nuevaPagina;
    $('#navMenu-' + paginaMostrada).addClass('activo');
    try {
        jsonString = JSON.parse(jsonString);
        console.log(jsonString);
        $('#contenedorPrincipal').html(jsonString.contenido);
        $('#contenedorPrincipal').addClass('pintar');
        //  if (jsonString.oculto !== '' && jsonString.oculto !== null) {
        $('#postJS').html(jsonString.oculto);
        // alert(jsonString.oculto);

    } catch (e) {
        alert(e);
    }
}




function url(pagina) {
    let aPoner = pagina;
    window.history.pushState("object or string", pagina, aPoner);
    buscarMedicos = '../buscarMedicos';
    emailServlet = '../cuenta';
}