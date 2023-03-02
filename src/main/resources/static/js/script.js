const toggleButtons = document.querySelectorAll('.toggle__btn');
toggleButtons.forEach(button => {
  button.addEventListener('click', () => {
    const target = document.querySelector(button.dataset.target);
    target.classList.toggle('hidden');
    target.style.transition = 'all 0.5s ease-in-out';
  });
});