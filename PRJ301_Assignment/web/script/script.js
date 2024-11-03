const showPopupBtn = document.querySelector(".login-btn");

const hidePopupBtn = document.querySelector(".form-popup .close-btn")

showPopupBtn.addEventListener("click", () => {
    document.body.classList.toggle("show-popup");
})

hidePopupBtn.addEventListener("click", () => showPopupBtn.click());