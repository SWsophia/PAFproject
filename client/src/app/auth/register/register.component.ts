import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RegisterPayload} from "../register-payload";
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm!: FormGroup


  constructor(private formBuilder:FormBuilder, private authService: AuthService,
              private router:Router) {

  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      username: this.formBuilder.control('', [Validators.required]),
      email: this.formBuilder.control('', [Validators.email]),
      password: this.formBuilder.control('',[Validators.required]),
      confirmPassword: this.formBuilder.control('', [Validators.required])

    })
  }

  onSubmit() {

    const registerPayload = this.registerForm.value as RegisterPayload
    console.log(registerPayload)

    this.authService.register(registerPayload).pipe()
    .subscribe(() => {
      console.log('register success')
      this.router.navigateByUrl('/register-success')})
  }

}
