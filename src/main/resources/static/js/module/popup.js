export default function initPopups() {
    const  OPEN_POPUP_BUTTON = document.querySelector('.openPopupJS');
    const  CLOSE_POPUP_BUTTON = document.querySelector('.closePopupJS');
    const  POPUP = document.querySelector('.popupJS');
    if (!OPEN_POPUP_BUTTON) {
        return;
    }
    initButton(OPEN_POPUP_BUTTON)
    initButton(CLOSE_POPUP_BUTTON)
    function initButton(button) {
        button.addEventListener('click', function(){
            POPUP.classList.toggle('hidden')
            document.querySelector('body').classList.toggle('overflowHidden')
        });
    }
}

