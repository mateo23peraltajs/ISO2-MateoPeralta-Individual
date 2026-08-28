document.getElementById("MyForm").addEventListener('submit',function(event){
    event.preventDefault()
    const username = document.getElementById('username').value
    const email = document.getElementById('email').value

    if (username.length < 8){
        console.log('el usuario debe tener mas de 8 caracteres')
        return //Se deja de ejecutar
    }
    if(username && email){
        console.log(`Usuario: ${username}`)
        console.log(`Email: ${email}`)
    }else{
        console.log('Algunos campos estan vacios')
    }
})