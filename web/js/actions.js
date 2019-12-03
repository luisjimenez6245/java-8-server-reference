function onClickDatabases() {}

function onClickCheckSyntax() {
  let q = $(".CodeMirror")[0].CodeMirror.getValue();
  if (q !== undefined && q !== "") {
    showLoading();
    let params = {
      c_action: "check_syntax",
      query: q
    };
    requestHandler("/", "POST", params, false);
    hideLoading();
  }
}

function onClickAutoComplete(query) {
  var q = $(".CodeMirror")[0].CodeMirror.getValue();
  if (q !== undefined && q !== "") {
    q += ' '; 
  }
  q += query;
  $(".CodeMirror")[0].CodeMirror.setValue(q);
  onAutoComplete();
}

function onAutoComplete() {
  let q = $(".CodeMirror")[0].CodeMirror.getValue();
  let params = {
    c_action: "auto_complete",
    query: q
  };
  requestHandler("/", "POST", params, true);
}
