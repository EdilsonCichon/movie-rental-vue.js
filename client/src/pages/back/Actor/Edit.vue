<template>
  <div>
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
      <h1 class="h2">Ator <small class="operation">Alterar</small></h1>
      <div class="btn-toolbar mb-2 mb-md-0">
        <button @click="updateAllowed = !updateAllowed" class="btn btn-sm btn-outline-secondary mr-2">
          <span data-feather="edit-3"></span>
          Editar
        </button>
        <button @click="handleRemove" class="btn btn-sm btn-outline-secondary mr-2">
          <i data-feather="trash-2"></i>
          Excluir
        </button>
      </div>
    </div>
    <div>
      <alert :type="messageType" :message="message" v-if="hasMessage"/>
      <form v-on:submit.prevent="handleUpdate">
        <div class="form-row">
          <div class="form-group col-md-12">
            <label for="name">Nome</label>
            <input name="name" type="text" class="form-control" id="name" placeholder="Nome" v-model="actor.name" :disabled="!updateAllowed" />
          </div>
        </div>
        <div class="text-right">
          <button type="submit" class="btn btn-primary alignle" :disabled="!updateAllowed">Salvar</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
  import { get, remove, update } from '@/apis/Actor'

  export default {
    name: 'CustomerEdit',

    data () {
      return {
        actor: {
          _id: '',
          name: ''
        },
        message: '',
        messageType: 'success',
        updateAllowed: false
      }
    },

    mounted () {
      get(this.$route.params.id)
        .then(data => {
          this.actor = data
        })
    },

    methods: {
      handleRemove () {
        let confirmation = confirm('Tem certeza que deseja cancelar esse Ator?')
        if (!confirmation) return
        remove(this.$route.params.id)
          .then(() => {
            this.handleSuccess('Ator deletado com sucesso!')
          })
          .catch(this.handleError)
      },
      handleUpdate () {
        update(this.$route.params.id, this.actor)
          .then(() => {
            this.handleSuccess('Ator editado com sucesso!')
          })
          .catch(this.handleError)
      },
      handleError (error) {
        this.message = error.response.data.message
        this.messageType = 'error'
      },
      handleSuccess (message) {
        this.message = message
        this.messageType = 'success'
      }
    },

    computed: {
      hasMessage () {
        return !!this.message
      }
    }
  }
</script>
