$(function () {
  var baseUrl = 'http://localhost:5000/'
  var factories = $('ul.factories')

  test();

  function test() {
    factories.append('<li> test </li>');
  }

  renderList();

  function renderList() {
    $.getJSON({
      url: baseUrl + 'factory/list/'
    }).done(function (data) {
      console.log(data);
      factories.empty();
      data.forEach(function (factory) {
        factories.append('<li>' + factory.name + '</li>');
      })
    }).fail(function () {
      console.log("error");
    });
  }

})
