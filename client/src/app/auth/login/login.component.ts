import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LoginPayload} from "../login-payload";
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup

  constructor(private fb:FormBuilder,
              private  authSrv:AuthService) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: this.fb.control('', [Validators.required]),
      password: this.fb.control('',[Validators.required])

    })
  }

  onSubmit(){
    const payload = this.loginForm.value as LoginPayload
    // const username = payload.username
    // const password = payload.password
    this.authSrv.login(payload).subscribe(result => {
      if(result) {
        console.log('login success')
      }else {
        console.log("Login failed")
      }
    })
  }

}
