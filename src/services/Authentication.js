'use strict'

export function handleLogin () {
  let vm = this
  if (this.username === 'ecichon' && this.password === '123456') {
    vm.messageValidate = ''
    vm.$router.push({name: 'back.dashboard'})
  } else {
    vm.messageValidate = 'Login inválido!'
  }
}

export function handleLogout () {
  console.error('//TODO Implements logout')
}
