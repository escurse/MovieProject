const $main = document.getElementById('main');
const $ulMain = $main.querySelector(':scope > .img > .main');
const $items = Array.from($ulMain.querySelectorAll(':scope > .item'));
const $itemContainer = $main.querySelector(':scope > .img > .item-container');
const $containerItems = Array.from($itemContainer.querySelectorAll(':scope > .item'));

$items.forEach(($item) => {
    $item.onclick = () => {
        $items.forEach((x) => x.classList.remove('select'));
        $item.classList.add('select');
    }
});

$containerItems.forEach(($item) => {
    $item.onclick = () => {
        $containerItems.forEach((x) => x.classList.remove('select'));
        $item.classList.add('select');
    }
})
