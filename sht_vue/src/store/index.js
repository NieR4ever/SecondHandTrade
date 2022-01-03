import { createStore } from 'vuex'

export default createStore({
    state: {
        loginUser: {},
        postCategory: 0,
    },
    mutations: {

        saveLoginUser(state, user) {
            localStorage.setItem("user",JSON.stringify(user))
            state.loginUser = JSON.parse(localStorage.getItem("user"))
        },
        changePostCategory(state, i) {
            state.postCategory = i
            console.log('2222');
        }
    },
    actions: {
    },
    modules: {
    }
})
