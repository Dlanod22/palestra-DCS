function toggleInsertModal()
{
    var modal = document.querySelector("#insert-modal");
    modal.hidden = !modal.hidden;
}

function openUpdateModal(id, nome, cognome, dataNascita, idPiano)
{
    document.querySelector("#update-modal").hidden = false;
    var modId = document.querySelector("#mod-id");
    var modNome = document.querySelector("#mod-nome");
    var modCognome = document.querySelector("#mod-cognome");
    var modDataNascita = document.querySelector("#mod-datanascita");
    var modPiano = document.querySelector("#mod-pianoAbbonamento");

    modId.value = id;
    modNome.value = nome;
    modCognome.value = cognome;
    modDataNascita.value = dataNascita;
    modPiano.value = idPiano;
}

function closeUpdateModal() 
{
    document.querySelector("#update-modal").hidden = true;
}


