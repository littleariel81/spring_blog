const token = searchParam('token');
if(token){
    localStorage.setItem("access_token", token);
}

function searchParam(key){
    return new URLSearchParams(location.search).get(key);
}

const params = new URLSearchParams(window.location.search);
if (params.get("logoutSuccess") === "true") {
    alert("You are logged out!");
}


