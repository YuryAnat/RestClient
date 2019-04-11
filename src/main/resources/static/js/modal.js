$(document).on('show.bs.modal', '#editUser', function (e) {
    var id = $(e.relatedTarget).data('id');
    var login = $(e.relatedTarget).data('login');
    var name = $(e.relatedTarget).data('name');
    var email = $(e.relatedTarget).data('email');

    // var id = $(result.id);
    // var login = $(result.login);
    // var name = $(result.name);
    // var email = $(result.email);

    $('#id').val(id);
    $('#login').val(login);
    $('#name').val(name);
    $('#email').val(email);
});
