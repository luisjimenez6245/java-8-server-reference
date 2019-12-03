let URL = "http://localhost:8080/sqlParser";

function deleteContent(id) {
  if (id) {
    $(id).remove();
  }
}

function showLoading() {
  $("#modal-loading").addClass("container-loading");
  $("#modal-loading").removeClass("container-hidden");
}

function hideLoading() {
  $("#modal-loading").addClass("container-hidden");
  $("#modal-loading").removeClass("container-loading");
}
function hideLeft() {
  $("#left-container").addClass("container-hidden");
  $("#left-container").removeClass("left-container");
}

function showLeft() {
  $("#left-container").addClass("left-container");
  $("#left-container").removeClass("container-hidden");
}

function showModal() {
  $("#modal-container").addClass("container-modal");
  $("#modal-container").removeClass("container-hidden");
}

function hideModal() {
  $("#modal-container").addClass("container-hidden");
  $("#modal-container").removeClass("container-modal");

  word_image = null;
  word_reference = null;
  word_comments = null;

  toolBar = {};
  options = [];
}

function screenHandler(json) {
  try {
    if (json !== null) {
      if (json.modal !== null && json.modal !== "") {
        $("#modal-container").html(json.modal);
      }
      if (json.content !== null && json.content !== "") {
        $("#main-container").html(json.content);
      }
      if (json.script !== null && json.script !== "") {
        $("#js-handler").html(json.script);
      }
    }
  } catch (e) {
    console.log(e);
  }
  return;
}

function validate_email(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}

function requestHandler(url, method, params, op) {
  $.ajax({
    url: URL + url,
    method: method,
    data: params,
    success: function(res) {
      if (res !== null && res !== "") {
        if (op) {
          appendHandler(res);
        } else {
          window.history.pushState("object or string", url, URL + url);
          screenHandler(res);
        }
      } else {
        alert("Se requiere volver a iniciar sesión");
        exit();
      }
    }
  });
}

function exit() {
  window.location.href = URL + "/log.jsp?ex=sdsa";
}

function appendHandler(json) {
  try {
    if (json !== null) {
      $("#js-handler").html(json.script);
      $(json.modal).append(json.content);
    }
  } catch (e) {
    console.log(e);
  }

  return;
}

function changeFile() {
  var file = document.querySelector("#img_input").files[0];
  var preview = document.querySelector("#image-container");
  var reader = new FileReader();
  reader.onloadend = function() {
    preview.style.backgroundImage = "url(" + reader.result + ")";
  };
  if (file) {
    reader.readAsDataURL(file);
  } else {
    preview.style.backgroundImage = "url()";
  }
}

function uploadFile(blob) {
  if (blob) {
    showLoading();
    let url = `${url}/file/`;
    $.ajax({
      url: url,
      type: "POST",
      data: new FormData($("#photo-file-wrapper")[0]),
      cache: false,
      contentType: false,
      processData: false,
      xhr: function() {
        var myXhr = $.ajaxSettings.xhr();
        return myXhr;
      },
      success: function(res) {
        addImage(res.url);
      }
    });
  }
}
