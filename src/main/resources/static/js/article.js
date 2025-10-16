const deleteButton = document.getElementById('delete-btn');
if(deleteButton){
    deleteButton.addEventListener('click', event=>{
        let id=document.getElementById('article-id').value;

       function success(){
           alert('Success Delete!!!');
           location.replace('/articles');
       }

       function fail(){
          alert('Failed Delete!!!');
          location.replace('/articles');
       }

        httpRequest('DELETE', `/api/article/${id}`, null, success, fail);

//        fetch(`/api/article/${id}`,{
//        method:'DELETE'
//        })
//        .then(()=>{
//            alert('Delete is completed!');
//            location.replace('/articles');
//        });
    }
);}


const modifyButton = document.getElementById('modify-btn');

if(modifyButton){
modifyButton.addEventListener('click', event=>{
        let params = new URLSearchParams(location.search);
        let id = params.get('id');
        body = JSON.stringify({
                title:document.getElementById('title').value,
                content:document.getElementById('content').value,
                });

        function success(){
            alert('Success Modification!!!');
            location.replace('/article/'+id);
        }

        function fail(){
           alert('Failed Modification!!!');
           location.replace('/article/'+id);
         }

         httpRequest('PUT', `/api/article/${id}`, body, success, fail);

//        fetch(`/api/article/${id}`,{
//          method:'PUT',
//          headers:{
//            'Content-Type':'application/json'
//          },
//          body: JSON.stringify({
//            title:document.getElementById('title').value,
//            content:document.getElementById('content').value
//          })
//        })
//        .then(()=>{
//           alert('Modifying is completed!');
//           location.replace(`/article/${id}`);
//        });
    }
);
}

const createButton=document.getElementById('create-btn');

if(createButton){
    createButton.addEventListener('click', (event)=>{
        body = JSON.stringify({
            title:document.getElementById('title').value,
            content:document.getElementById('content').value,
            });

        function success(){
            alert('Success Registration!!!');
            location.replace('/articles');
        }

        function fail(){
           alert('Failed Registration!!!');
           location.replace('/articles');
         }

     httpRequest('POST', '/api/articles', body, success, fail);

//        fetch('api/articles',{
//            method:'POST',
//            headers:{
//                'Content-Type':'application/json',
//            },
//            body: JSON.stringify({
//                title:document.getElementById('title').value,
//                content:document.getElementById('content').value,
//            }),
//        }).then(()=>{
//            alert('Creating is completed');
//            location.replace('/articles');
//        });

    });
}

function getCookie(key){
    var result = null;
    var cookie = document.cookie.split(';');
    cookie.some(function (item){
        item = item.replace(' ', '');
        var dic = item.split('=');
        if(key === dic[0]){
            result = dic[1];
            return true;
        }
    });
    return result;
}

function httpRequest(method, url, body, success, fail){
    fetch(url, {
        method: method,
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem('access_token'),
            'Content-Type': 'application/json',
        },
        body: body,
    }).then( response => {
        if(response.status===200 || response.status === 201){
            return success();
        }
        const refresh_token = getCookie('refresh_token');
        if(response.status === 401 && refresh_token){
            fetch('/api/token', {
                method: 'POST',
                headers: {
                   Authorization : 'Bearer ' + localStorage.getItem('access_token'),
                   'Content-Type' : 'application/json',
                },
                body: JSON.stringify({
                    refreshToken: getCookie('refresh_token'),
                }),
            }).then(res => {
                if(res.ok){
                    return res.json();
                }
            }).then(result => {
                localStorage.setItem('access_token', result.accessToken);
                httpRequest(method, url, body, success, fail);
            }).catch(error => fail());
        }else{
            return fail();
        }
    });
}