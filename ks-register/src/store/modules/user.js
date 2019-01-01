export default {
  namespaced: true,
  state: {
    id: 0,
    truename: '',
    idcard: '',
    examname: ''
  },
  mutations: {
    updateId (state, id) {
      state.id = id
    },
    updateTruename (state, truename) {
      state.truename = truename
    },
    updateIdcard (state, idcard) {
      state.idcard = idcard
    },
    updateExamname (state, examname) {
      state.examname = examname
    }
  }
}
