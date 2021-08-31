## About The Project

Listen my order is sample application from the [euphony library](https://github.com/euphony-io/euphony) for ordering food over acoustic data communication.
Our goal is to help restaurateurs set up low-cost food ordering systems instead of kiosks.

- #### Features
  - Export the menu list through the speaker.
  - Import the menu list via the microphone.
  - Add a menu to menu list. Menu information consists of name, price and description.

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/euphony-io/listen-my-order.git
   ```
2. Bulid the project by gradle wrapper
   ```sh
   ./gradlew build
   ```

## Usage

### Restaurant Owner

1. Press 'Export Menu' button
  
  ![그림7](https://user-images.githubusercontent.com/80624082/131519244-0db12a18-5eb6-44f8-a39c-f019a67c4d45.png)

2. Enter your restaurant name and click '+' button which locate below
  
  ![그림1](https://user-images.githubusercontent.com/80624082/131517465-b176ec7d-e258-468e-b309-190235d70be5.png)
  
3. Enter food name, food description and food price. Then click 'Save' button
  
  ![그림2](https://user-images.githubusercontent.com/80624082/131517757-a6238812-22a0-4d15-bc95-ec88489f9bd5.png)

4. Click 'Export Menu' button to generate sound signal.

  ![그림4](https://user-images.githubusercontent.com/80624082/131518447-a8ccccc9-f232-4849-8c0a-4f61c553c3f0.png)
  
5. 'Export Menu' will change to 'Exporting Menu...' and signal will be generated
  
  ![그림6](https://user-images.githubusercontent.com/80624082/131518837-7873aa98-fcd4-4d37-8d84-6aa4415bcefe.png)





## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

Distributed under the Apache 2.0. See [LICENSE](https://github.com/euphony-io/listen-my-order/blob/main/LICENSE) for more information.
