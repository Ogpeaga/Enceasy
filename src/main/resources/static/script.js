async function encurtarUrl(){

    const urlOriginal =
    document.getElementById("input").value;

    const urlPersonalizada = 
    document.getElementById("input-personalizada").value;


    try{

        const response = await fetch(
            "https://enceasy.onrender.com",
            {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },
                body:JSON.stringify({
                    urlOriginal: urlOriginal,
                    urlEncurtada: urlPersonalizada
                })
            }
        );

        if(response.status === 500){
            alert("URL personalizada já esta em uso!");
            
            return;

        }

        if (!response.ok){
            throw new Error("Erro ao encurtar URL");
        }
        
        const data = await response.json();

        const resultado =
            document.getElementById("resultado");

        resultado.innerText =
            data.urlEncurtada;

        resultado.href = 
            data.urlEncurtada;

        document.querySelector(".output").style.display = "block";
        
    }catch(Erro){
        alert("Erro ao conectar com o Servidor");
    }
    
}

document
    .querySelector(".button")
    .addEventListener("click",encurtarUrl);