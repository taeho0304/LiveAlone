<template>
    <nav>
        <v-app-bar color="blue darken-1" dark flat app>
            <v-toolbar-title>나 혼자 살거다</v-toolbar-title>


            <v-spacer></v-spacer>
            
            <v-btn class="btn" depressed rounded color="blue lighten-4 black--text" @click="search = !search">상세검색<v-icon>mdi-search-web</v-icon></v-btn>
            
            <v-btn class="btn" depressed rounded color="blue lighten-4 black--text" @click="signIn = !signIn">로그인</v-btn>
            <v-btn class="btn" depressed rounded color="blue lighten-4 black--text" @click="signUp = !signUp">회원가입</v-btn>
        </v-app-bar>

         <div v-if="search" style="margin-top:65px; background-color:white;">
                <v-item-group multiple>
    
                    <v-item
                    v-for="n in 8"
                    :key="n"
                    v-slot="{ active, toggle }"
                    >
                    <v-chip
                        active-class="purple--text"
                        :input-value="active"
                        @click="toggle"
                    >
                        Tag {{ n }}
                    </v-chip>
                    </v-item>
                </v-item-group>
        </div>

        <v-row justify="center">
            <v-dialog v-model="signIn" persistent max-width="600px" >
                <v-card style="border-radius: 30px; background: #D9E1E8;">
                    <v-card-title class="justify-center">
                        로그인
                    </v-card-title>
                   
                        <v-container style="margin: 1px auto 0px">
                            <v-col>
                                <v-text-field outlined clearable rounded dense label="아이디" required></v-text-field>
                                <v-text-field outlined clearable rounded dense label="비밀번호" type="password" required></v-text-field>
                            </v-col>
                        </v-container>
                   
                    <v-card-actions style="margin: 0px auto 0px">
                        <v-spacer></v-spacer>
                        <v-btn class="btn" depressed rounded color="red lighten-4 black--text" @click="signIn = !signIn">취소</v-btn>
                        <v-btn class="btn" depressed rounded color="blue lighten-4 black--text" @click="signIn = !signIn">확인</v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>

        <v-row justify="center">
            <v-dialog v-model="signUp" persistent max-width="600px">
                <v-card style="border-radius: 30px; background: #D9E1E8;">
                    <v-card-title class="justify-center">
                        회원가입
                    </v-card-title>
                        <v-container style="margin: 1px auto 0px">
                            <v-row>
                                <v-col cols="12">
                                     <v-text-field outlined clearable rounded dense label="이름" required></v-text-field>
                                     <v-text-field outlined clearable rounded dense label="아이디" required></v-text-field>
                                     <v-text-field outlined clearable rounded dense label="이메일" required></v-text-field>
                                     <v-text-field outlined clearable rounded dense label="비밀번호" type="password" required></v-text-field>
                                     <v-text-field outlined clearable rounded dense label="비밀번호 확인" type="password" required></v-text-field>
                                     <v-text-field outlined clearable rounded dense label="휴대폰 번호" type="password" required></v-text-field>
                                </v-col>
                            </v-row>
                        </v-container>
                   
                    <v-card-actions style="margin: 0px auto 0px">
                        <v-spacer></v-spacer>
                        <v-btn class="btn" depressed rounded color="red lighten-4 black--text" @click="signUp = !signUp">취소</v-btn>
                        <v-btn class="btn" depressed rounded color="blue lighten-4 black--text" @click="signUp = !signUp">확인</v-btn>
                    </v-card-actions>
                </v-card>
                
            </v-dialog>
        </v-row>


    </nav>

    
    
</template>

<script>
export default {
  name: 'MainPage',
  data(){
      return{
          signIn:false,
          signUp:false,
          search:false,
      }
  }
}
</script>

<style>
.btn{
    margin: 0px 0px 0px 16px;
    width:105px;
}

.v-text-field--filled.v-input--dense.v-text-field--single-line > .v-input__control > .v-input__slot, .v-text-field--filled.v-input--dense.v-text-field--outlined > .v-input__control > .v-input__slot, .v-text-field--filled.v-input--dense.v-text-field--outlined.v-text-field--filled > .v-input__control > .v-input__slot, .v-text-field--full-width.v-input--dense.v-text-field--single-line > .v-input__control > .v-input__slot, .v-text-field--full-width.v-input--dense.v-text-field--outlined > .v-input__control > .v-input__slot, .v-text-field--full-width.v-input--dense.v-text-field--outlined.v-text-field--filled > .v-input__control > .v-input__slot, .v-text-field--outlined.v-input--dense.v-text-field--single-line > .v-input__control > .v-input__slot, .v-text-field--outlined.v-input--dense.v-text-field--outlined > .v-input__control > .v-input__slot, .v-text-field--outlined.v-input--dense.v-text-field--outlined.v-text-field--filled > .v-input__control > .v-input__slot {
    min-height: 40px;
    background-color: white;
}


   
</style>
