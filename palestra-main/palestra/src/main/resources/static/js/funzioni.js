function toggleInsertModal()
{
    var modal = document.querySelector("#insert-modal");
    modal.hidden = !modal.hidden;
}

function openUpdateModal(id, nome, cognome, dataNascita, idClasse)
{
    document.querySelector("#update-modal").hidden = false;
    var modId = document.querySelector("#mod-id");
    var modNome = document.querySelector("#mod-nome");
    var modCognome = document.querySelector("#mod-cognome");
    var modDataNascita = document.querySelector("#mod-datanascita");
    var modClasse = document.querySelector("#mod-classe");

    modId.value = id;
    modNome.value = nome;
    modCognome.value = cognome;
    modDataNascita.value = dataNascita;
    modClasse.value = idClasse;
}

function toggleUpdateUsernameAndPasssword()
{
    var modal = document.querySelector("#update-user-pass-modal");
    modal.hidden = !modal.hidden;
}